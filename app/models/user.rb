class User < ActiveRecord::Base
  enum role: [:user, :coordinator, :teacher, :admin]
  after_initialize :set_default_role, :if => :new_record?
  before_validation :set_name_with_color

  def set_default_role
    self.role ||= :user
  end

  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable,
         :recoverable, :rememberable, :trackable, :validatable
  belongs_to :color
  validates :name, presence: true
  validates :color, presence: true
  validates :last_name, presence: true
  validates :name_with_color, uniqueness: true

  private

  def set_name_with_color
    self.name_with_color = self.name + ' ' + color.name
  end
end
